package com.l3azh.bonsai.Util;

import com.google.gson.Gson;
import com.l3azh.bonsai.Dto.Base.ErrorResponseDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;

public class AppUtils {
    public static String convertByteToBase64String(byte[] theInputByteArray) {
        return Base64.getEncoder().encodeToString(theInputByteArray);
    }

    public static byte[] convertStringBase64ToByteArray(String theInputStringEncode) {
        return Base64.getDecoder().decode(theInputStringEncode);
    }

    public static String getCurrentDateString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return df.format(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
    }

    public static void sendError(HttpServletResponse response, int code, String message) throws IOException {
        response.setHeader("Content-type", "application/json");
        response.setStatus(code);
        ErrorResponseDto responseDto = ErrorResponseDto.builder()
                .code(code)
                .flag(false)
                .errorMessage(message).build();
        response.getWriter().write(new Gson().toJson(responseDto));
    }
}
