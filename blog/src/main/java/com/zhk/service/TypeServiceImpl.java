package com.zhk.service;

import com.zhk.NotFoundException;
import com.zhk.dao.TypeRepositoury;
import com.zhk.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TypeServiceImpl  implements  TypeService {
    @Autowired
    private TypeRepositoury typerepositoury;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typerepositoury.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typerepositoury.getOne(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typerepositoury.findByName(name);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typerepositoury.findAll(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t=typerepositoury.getOne(id);
        if(t==null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t);
        return typerepositoury.save(t);
    }

    @Override
    public List<Type> listType() {
        return typerepositoury.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable=PageRequest.of(0,size,sort);
        return typerepositoury.findTop(pageable);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typerepositoury.deleteById(id);
    }
}
