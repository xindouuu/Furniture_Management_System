package com.jxd.furn.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxd.furn.bean.Furn;
import com.jxd.furn.service.FurnService;
import com.jxd.furn.util.Result;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
public class FurnController {
    @Resource
    private FurnService furnService;

    // to return the corresponding furniture information based on id
    // URL placeholders + @PathVariable
    @GetMapping("/find/{id}")
    public Result findById(@PathVariable Integer id) {
        Furn furn = furnService.getById(id);
        return Result.success(furn);
        //Return success information - Carry the queried furn information
    }



    @PostMapping("/save")
    public Result save(@Validated @RequestBody Furn furn, Errors errors) {

        HashMap<String, Object> map = new HashMap<>();

        List<FieldError> fieldErrors = errors.getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        if (map.isEmpty()) {
            furnService.save(furn);
            return Result.success();
        } else {
            return Result.error("400", "Backend Validation Failed", map);
        }

    }
    @RequestMapping("/furns")
    public Result listFurns() {
        List<Furn> furns = furnService.list();
        return Result.success(furns);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Furn furn) {
        //updateById is provided by mybatis-plus
        furnService.updateById(furn);
        return Result.success();
    }

    @DeleteMapping("/del/{id}")
    public Result del(@PathVariable Integer id) {
        //removeById is provided by Mybatis-Plus
        furnService.removeById(id);
        return Result.success();
    }

    @GetMapping("/furnsByPage")
    public Result listFurnsByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "5") Integer pageSize) {

        //Here, the page method is used to return a Page object.
        // The object encapsulates paginated data.
        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize));
        return Result.success(page);
    }

    /**
     * @param pageNum  Display page number
     * @param pageSize Number of records per page
     * @param search   Search criteria: Furniture name, default is "",
     *                 indicating no specific criteria for search, regular pagination
     * @return
     */
    @GetMapping("/furnsBySearchPage")
    public Result listFurnsByConditionPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "") String search) {

        //Create a QueryWrapper first, which allows us to encapsulate our search criteria
        QueryWrapper<Furn> queryWrapper = Wrappers.query();
        //Determine whether 'search' has content
        if (StringUtils.hasText(search)) {
            queryWrapper.like("name", search);
        }

        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize), queryWrapper);

        return Result.success(page);
    }

}
