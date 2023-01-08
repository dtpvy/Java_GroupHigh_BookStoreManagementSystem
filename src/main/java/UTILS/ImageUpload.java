package UTILS;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.exceptions.*;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.results.Result;
import io.imagekit.sdk.utils.Utils;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageUpload {
    public static ImageKit imageKit = null;
    public ImageUpload() {
        imageKit = ImageKit.getInstance();
        Configuration config = new Configuration("public_xTbc2crb6gXYxB5gtKroms4tWCU=", "private_Qr0rq1pwvFbopF819AptlFr6fX8=", "https://ik.imagekit.io/0o9nfg6a3");
        imageKit.setConfig(config);
    }
    public static String getFileName(String filePath) {
        File file = new File(filePath);
        return file.getName();
    }
    public static String upload(String filePath) {
        Result result = null;
        try {
            String base64 = Utils.fileToBase64(new File(filePath));
            FileCreateRequest fileCreateRequest = new FileCreateRequest(base64, getFileName(filePath));
            String customCoordinates = "10,10,20,20";
            fileCreateRequest.setCustomCoordinates(customCoordinates);
            result = imageKit.upload(fileCreateRequest);
            System.out.println(result);
            return result.getUrl();
        } catch (Exception e) {}
        return "https://ik.imagekit.io/0o9nfg6a3/loginbg_M39x117vT.png?ik-sdk-version=javascript-1.4.3&updatedAt=1673189879987";
    }
}
