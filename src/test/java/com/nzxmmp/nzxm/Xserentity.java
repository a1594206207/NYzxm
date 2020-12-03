package com.nzxmmp.nzxm;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Xserentity {
    @JsonFormat(pattern = "yyyy/MM/dd")
    Date time;
}
