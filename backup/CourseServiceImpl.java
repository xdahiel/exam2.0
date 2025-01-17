package cn.cuit.exam.service.impl;

import cn.cuit.exam.bean.Course;
import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.vo.CourseQuery;
import cn.cuit.exam.mapper.CourseMapper;
import cn.cuit.exam.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    @Transactional
    public PageBean<Course> queryCourse(CourseQuery courseQuery) {
        // 查询总记录条数
        int totalCount = courseMapper.queryNumOfSelected(courseQuery.getCno());
        // 查询课程集合
        List<Course> courseList = courseMapper.queryCourse(courseQuery);
        // 创建pageBean
        PageBean<Course> pageBean = new PageBean<Course>(totalCount, courseList, courseQuery.getPageSize(), courseQuery.getPageNum());

        return pageBean;
    }

    @Override
    public int addCourse(Course course) {
        // 判断该课程是否存在
        if (courseMapper.queryByCno(course.getCno()) != null ) return 0;

        // 添加课程
        int count = courseMapper.insertCourse(course);
        return count;
    }

    @Override
    public int deleteCourse(String cno) {
        if (courseMapper.queryByCno(cno) < 1) return 0;

        return courseMapper.deleteCourse(cno);
    }

    @Override
    public int deleteCourseList(List<String> cnoList) {
        int flag = 1;
        for (String cno : cnoList) {
            flag &= deleteCourse(cno);
        }
        return flag;
    }

//    @Override
//    public int insertTeach(Teach teach) {
//        List<TeachQuery> teaches = courseMapper.queryTeach(new TeachQuery(teach.getCno(), teach.getTno(), teach.getCid()));
//        if (teaches.size() != 0) return 0;
//        return courseMapper.insertTeach(teach);
//    }

    @Override
    public int deleteTeachByCno(String cno) {
        return courseMapper.deleteTeachByCno(cno);
    }

    @Override
    public int deleteTeachByTno(String tno) {
        return courseMapper.deleteTeachByTno(tno);
    }

}
