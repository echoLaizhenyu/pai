import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lai.pai.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test {

    private static final String tab = "\t";//tab
    private static final String br = System.getProperty("line.separator");//换行
    public static void main(String[] args) {

        String menu = readFileByLines("E://daqiaomenu.json");

        JSONArray array = JSONArray.parseArray(menu);

        System.out.println(array);
        StringBuffer sbf = new StringBuffer();
        String menu1 = getMenu(array,sbf,0);

        System.out.println(menu1);

    }


    public static String getMenu(JSONArray array, StringBuffer sbf,int tabNum){

        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            sbf.append(StringUtils.mulStr(tab,tabNum));
            sbf.append(jsonObject.get("moduleName"));
            String childs = StringUtils.getStrFromNull(jsonObject.get("childs")+"");
            if(!"".equals(childs)){
                JSONArray arrayChild = JSONArray.parseArray(childs);
                sbf.append(br);
//                sbf.append(StringUtils.mulStr(tab,tabNum+1));
                getMenu(arrayChild,sbf,tabNum+1);

            }
            if(i!=array.size()-1){
                sbf.append(br);
            }

        }
        return sbf.toString();
    }


    public static String readFileByLines(String fileName) {
        StringBuffer sbf = new StringBuffer();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                sbf.append(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sbf.toString();
    }

}
