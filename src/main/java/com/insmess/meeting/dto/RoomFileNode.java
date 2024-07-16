package com.insmess.meeting.dto;

import com.insmess.meeting.entity.RoomFile;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoomFileNode extends RoomFile {
    private List<RoomFileNode> children = new ArrayList<>();
}
