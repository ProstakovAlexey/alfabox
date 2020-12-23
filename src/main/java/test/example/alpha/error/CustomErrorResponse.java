package test.example.alpha.error;

import lombok.Data;

@Data
public class CustomErrorResponse {
    private int errorCode;
    private String errorDetail;
}