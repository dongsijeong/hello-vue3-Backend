package demo.mo.model;

import lombok.Data;

@Data
public class MessageModel {
    private String messageId;
    private int rowNumber;
    private String[] params;
    private String fleldName;
}
