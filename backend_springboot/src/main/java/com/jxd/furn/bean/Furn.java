package com.jxd.furn.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@TableName("furn")
public class Furn {

    // @TableId: Table primary key identifier
    // When we annotate private Integer id with @TableId
    // It means that id corresponds to the id field of the table and is the primary key
    // type = IdType.AUTO indicates that the primary key type is auto-increment

    @TableId(type = IdType.AUTO)
    private Integer id;


    // If it is non-empty validation for String, we should use @NotEmpty
    @NotEmpty(message = "Please input furniture name")
    private String name;
    @NotEmpty(message = "Please input maker")
    private String maker;

    @NotNull(message = "Please input number")
    @Range(min = 0, message = "Price cannot be less than 0")
    private BigDecimal price;

    @NotNull(message = "Please input number")
    @Range(min = 0, message = "Sales cannot be less than 0")
    private Integer sales;

    @NotNull(message = "Please input number")
    @Range(min = 0, message = "Stock cannot be less than 0")
    private Integer stock;
}
