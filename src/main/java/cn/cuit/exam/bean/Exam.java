package cn.cuit.exam.bean;

import cn.cuit.exam.bean.common.Utils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
public class Exam implements Serializable {
    // 请求 ： 课程号、场次、考试日期、考试时长，班级列表

    private int eno;

    @ApiModelProperty(value = "课程号")
    private String cno;

    @ApiModelProperty(value = "场次")
    private int cnt;
    private String site;

    @ApiModelProperty(value = "考试日期", hidden = true)
    private Calendar day;   //有效值（年、月、日）

    private Calendar start; //有效值（时、分）
    private Calendar end;


    private String teacher1;
    private String teacher2;

    private int state;
    private int duration;

    private String patrol1;
    private String patrol2;

    private int type;

    @ApiModelProperty(value = "学院", example = "软件工程")
    private String school;

    public Exam() {}

    /**
     * 获取该场次考试的周数
     */
    public int getWeek() {
        Calendar init = Utils.getInitDate();
        int week = (int) (day.getTimeInMillis() - init.getTimeInMillis()) / (1000 * 3600 * 24 * 7);
        return week;
    }

    /**
     * 考试当天是星期几
     */
    public int getWeekDay() {
        Calendar init = Utils.getInitDate();
        int week = this.getWeek();
        int weekdays = (int) (day.getTimeInMillis() - init.getTimeInMillis()) / (1000 * 3600 * 24);
        return weekdays % week + 1;
    }

}
