import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lai.pai.PaiApplication;
import com.lai.pai.entity.ACode;
import com.lai.pai.entity.ADayInfo;
import com.lai.pai.mapper.ACodeMapper;
import com.lai.pai.mapper.ADayInfoMapper;
import com.lai.pai.service.IADayInfoService;
import com.lai.pai.util.CommonUtil;
import com.lai.pai.util.HttpUtil;
import com.lai.pai.util.HttpUtil2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PaiApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCode {

    @Autowired
    private ACodeMapper aCodeMapper;

    @Autowired
    private ADayInfoMapper aDayInfoMapper;

    @Autowired
    private IADayInfoService iaDayInfoService;
    @Test
    public void testCode(){

        String str = CommonUtil.readFileByLines("G://data/code.txt");
        JSONArray array = JSONArray.parseArray(str);
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = (JSONObject) array.get(i);
            String code = jsonObject.getString("f12");
            String name = jsonObject.getString("f14");
            ACode acode = new ACode();
            acode.setCode(code);
            acode.setName(name);
            QueryWrapper<ACode> wrapper = new QueryWrapper<>();
            wrapper.eq("code",code);
            int count = aCodeMapper.selectCount(wrapper);
            if(count==0){
                aCodeMapper.insert(acode);
            }

        }

    }

    @Test
    public void testCodeInfo() throws Exception {

        Wrapper<ACode> wrapperA = new QueryWrapper<>();
        List<ACode> aCodes = aCodeMapper.selectList(wrapperA);

        for (int i = 0; i < aCodes.size(); i++) {
            ACode aCode = aCodes.get(i);
            boolean result = getInfo(aCode.getCode());
            if(!result){
                aCode.setStatus("2");
                aCodeMapper.updateById(aCode);
            }
        }
    }

    public boolean getInfo(String aCode){
        boolean result =true;

        if(aCode.startsWith("6")){
            aCode = "0"+aCode;
        }else {
            aCode = "1"+aCode;
        }

        try{
            String url = "https://img1.money.126.net/data/hs/kline/day/history/2021/"+aCode+".json";
            String s = HttpUtil.doGet(url);
            JSONObject jsonObject = JSONObject.parseObject(s);

            String symbol = jsonObject.get("symbol")+"";
            String name = jsonObject.get("name")+"";

            JSONArray array = (JSONArray) jsonObject.get("data");

            List<ADayInfo> list = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                JSONArray array2 = (JSONArray)array.get(i);
                if(array2.size()==7){
                    String date = array2.getString(0);
                    String startPrice = array2.getString(1);
                    String endPrice = array2.getString(2);
                    String highPrice = array2.getString(3);
                    String lowPrice = array2.getString(4);
                    String tradeNum = array2.getString(5);
                    String percent = array2.getString(6);

                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

                    ADayInfo aDayInfo = new ADayInfo();
                    aDayInfo.setCode(symbol);
                    aDayInfo.setName(name);
                    aDayInfo.setDate(format.parse(date));
                    aDayInfo.setStartPrice(Double.parseDouble(startPrice));
                    aDayInfo.setEndPrice(Double.parseDouble(endPrice));
                    aDayInfo.setHighPrice(Double.parseDouble(highPrice));
                    aDayInfo.setLowPrice(Double.parseDouble(lowPrice));
                    aDayInfo.setPercent(percent);
                    aDayInfo.setTradeNum(Integer.parseInt(tradeNum));

                    list.add(aDayInfo);
                    /*QueryWrapper<ADayInfo> wrapper = new QueryWrapper<ADayInfo>();
                    wrapper.eq("code",symbol);
                    wrapper.eq("date",format.parse(date));
                    int count = aDayInfoMapper.selectCount(wrapper);*/

                    /*if(count==0){
                        aDayInfoMapper.insert(aDayInfo);
                        System.out.println("insert "+symbol+" date:"+date);
                    }*/

                }
            }
            iaDayInfoService.saveBatch(list);
            System.out.println("insert "+symbol);
            Thread.sleep(100);
        }catch (Exception e){
            System.out.println("error====================== "+aCode);
//            e.printStackTrace();
            result =false;
        }
        return result;
    }


    @Test
    public void getTradeInfo2(){
        String url = "http://quotes.money.163.com/service/chddata.html?code=0601156&start=20210101&end=20211231&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP";
        try {
            String s = HttpUtil2.doGet(url);
            System.out.println(s);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
