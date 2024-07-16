package com.insmess.meeting.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    //唯一标识
    private String username;
    //真实姓名，界面显示
    private String realname;
}
