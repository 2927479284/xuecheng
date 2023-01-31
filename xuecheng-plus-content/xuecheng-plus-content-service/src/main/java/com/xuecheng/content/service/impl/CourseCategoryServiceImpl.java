package com.xuecheng.content.service.impl;

import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 课程分类 服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

    @Autowired
    private CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryTreeDto> queryTreeNodes(String id) {

        // 1.查到根节点下面所有子节点
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes(id);

        // 2.定义一个List作为最终返回数据
        List<CourseCategoryTreeDto> categoryTreeDtos = new ArrayList<>();

        // 3.定义一个Map,方便直接查询到子节点的父节点
        Map<String,CourseCategoryTreeDto> map = new HashMap<>();

        // 4.将数据封装止list中,只包含根节点的直接子节点(父->子)
        courseCategoryTreeDtos.forEach(item ->{
            map.put(item.getId(),item);
            if (item.getParentid().equals(id)){
                categoryTreeDtos.add(item);
            }
            // 5.寻找当前节点的父节点id
            String parentid = item.getParentid();
            // 6.通过父节点id寻找父节点对象
            CourseCategoryTreeDto courseCategoryTreeDto = map.get(parentid);
            if (courseCategoryTreeDto!=null){
                List<CourseCategoryTreeDto> childrenTreeNodes = courseCategoryTreeDto.getChildrenTreeNodes();
                if (childrenTreeNodes == null){
                    courseCategoryTreeDto.setChildrenTreeNodes(new ArrayList<>());
                }else {
                    childrenTreeNodes.add(item);
                }

            }
        });
        return categoryTreeDtos;
    }
}
