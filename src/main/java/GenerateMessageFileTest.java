
import com.sun.deploy.util.StringUtils;
import com.sun.org.apache.xerces.internal.xs.StringList;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateMessageFileTest{//extends BaseJunit4Test

    /**
    * @Description: 测试根据 settDate 分组生成报文
    * @author zhuliangbing
    * @date 2018/4/12 18:49
    */
    public void testGroup() {
        System.out.println("测试Spring整合Junit4进行单元测试");
        try {
            List<Long> recordIdList = new ArrayList<Long>();
//            recordIdList.add(1L);
//            recordIdList.add(2L);
//            recordIdList.add(3L);
//            recordIdList.add(4L);
//            recordIdList.add(5L);
            recordIdList.add(8065L);
            recordIdList.add(10001L);
            recordIdList.add(10003L);
            recordIdList.add(10005L);
            recordIdList.add(10006L);

            long startTime=System.currentTimeMillis();   //获取开始时间

            //真正执行的程序
//            GenMessageFileReturnVO genMessageFileReturnVO = new GenMessageFileReturnVO();
//            genMessageFileReturnVO.setGenerateFlag(TaskStatusEnum.SUCCESS.getValue());
//            messageFileService.generateMessageFiles(genMessageFileReturnVO, null, 1);
            //Assert.assertNotNull(genMessageFileReturnVO);

            long endTime=System.currentTimeMillis(); //获取结束时间
            System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void test1(){
        String lineTXT = "1046Axx            492F1706291000300103002                    4301002017070320200623CNY00000770000000077000000007700020336 27 201803232018032300000025060000002506000005997200000000000000000000000000000000000000000000000000000000010����                    00000000001�����                        042102319730116833X                              B1197301162060013787027381              13787027381                                                            ��ɳ��ɳ����54��460���ŵ�                                   410000����ʡ��������ľ������2-30��                                ���㷼                        0422425197310218322                                                            13070975546              CY��ˮ������                                                   ��ɳ��ɳ����                                                999999201549120000                                                          D��ɳ��ɳ����54��460���ŵ�                                   4100001F00000000                                1111111       20180306{基础段[24个月（账户）还款状态]数据项类型为AN或ANC的必填数据项，数据项值不能为空且不能为空格（含全角和半角空格）,数据项值必须在编码范围里。可选型数据项，无法填报时必须用半角空格（0X20）填充:错误代码7107013}{职业信息段[单位所属行业]数据项类型为AN或ANC的必填数据项，数据项值不能为空且不能为空格（含全角和半角空格）,数据项值必须在编码范围里。可选型数据项，无法填报时必须用半角空格（0X20）填充:错误代码6103013}{职业信息段[单位所属行业]字段不在数据字典列表中:错误代码6103302}";
        // 提取每行中的错误信息
        List<String> errorInfoList = extractMessage(lineTXT);
        // 创建一个 map，以 errorInfo 中的 errorCode 为 key，errorDesc 为 value。用于存储一个账户记录中出现的所有错误信息。
        Map<String,String> errorInfoMap = new HashMap<String, String>();
        String[] errorInfoArray;
        for(String errorInfo: errorInfoList){
            errorInfoArray = errorInfo.split(":错误代码");
            errorInfoMap.put(errorInfoArray[0], errorInfoArray[1]);
        }
        System.out.println(errorInfoMap);
    }


    /**
     * @Description: 提取大括号 {} 中内容，忽略大括号中的大括号
     * @author zhuliangbing
     * @date 2018/4/19 17:37
     */
    public static List<String> extractMessage(String msg) {

        List<String> list = new ArrayList<String>();
        int start = 0;
        int startFlag = 0;
        int endFlag = 0;
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == '{') {
                startFlag++;
                if (startFlag == endFlag + 1) {
                    start = i;
                }
            } else if (msg.charAt(i) == '}') {
                endFlag++;
                if (endFlag == startFlag) {
                    list.add(msg.substring(start + 1, i));
                }
            }
        }
        return list;
    }


    public void test2(){

        // 提取金融机构代码、业务号以及结算/应还款日期
        Map<String, String> map = new HashMap<String, String>();
        String financeCode = "xx";
        String accountCode = "F1707041001300101001";
        String settDate = "20180403";
        map.put("financeCode",financeCode);
        map.put("accountCode",accountCode);
        map.put("settDate",settDate);

        // 将错误信息所指的 recordId 定位到
        System.out.println("recordId 为：");

    }

    public void test3(){
        String lineTXT = "0983Axx            492F1707041001300101001                    3601002017070720200703CNY00000466800000046680000004668020336 27 201804032018040300000015190000001519000003635700000000000000000000000000000000000000000000000000000000111///////////////NNN1N1NNN00000000001��Ƽ��                        0360311197102042535                              B1197102041060015979229365              15979229365                                                            �����ظ��������嵱ͷ������ݳ�38��                        337009�����ظ��������嵱ͷ������ݳ�38��                                                                                                                                                              C6���˻�������                                                F�����ظ��������嵱ͷ������ݳ�38��                        99999920024972000                                                           D�����ظ��������嵱ͷ������ݳ�38��                        3370091\n";
        Map<String, String> map = new HashMap<String, String>();
        String financeCode = StringUtils.trimWhitespace(lineTXT.substring(5,19));
        String accountCode = StringUtils.trimWhitespace(lineTXT.substring(22,62));
        String settDate = StringUtils.trimWhitespace(lineTXT.substring(126,134));
        map.put("financeCode",financeCode);
        map.put("accountCode",accountCode);
        map.put("settDate",settDate);
        System.out.println(map);
        // 将错误信息所指的 recordId 定位到
        System.out.println("recordId 为：");
    }

    /**
    * @Description: 查看系统默认编码
    * @author zhuliangbing
    * @date 2018/4/28 16:23
    */
    public void testChar()
    {
        //方法一：中文操作系统中打印GBK
        System.out.println(System.getProperty("file.encoding"));

        //方法二：中文操作系统中打印GBK
        System.out.println(Charset.defaultCharset());
    }



}
