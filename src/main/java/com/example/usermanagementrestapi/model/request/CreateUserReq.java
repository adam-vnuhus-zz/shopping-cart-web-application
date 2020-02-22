package com.example.usermanagementrestapi.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserReq {
    @NotEmpty(message = "Tên không được rỗng")
    @ApiModelProperty(
            example="Sam Smith",
            notes="Full name cannot be empty",
            required=true
    )
//    Cho sẵn mẫu để bên phía FR có thể gửi lên theo đúng yêu
//    Cấu hình giá trị mẫu
    //@JsonProperty("full_name")
    //Để cho phía Frontend(FE) không cần nhập đúng định dạng fullName khi req server
    private String fullName;

    @Email
    @ApiModelProperty(
            example="ahaha@gmail.com",
            notes="Đúng định dạng email",
            required=true
    )
    private String email;

    @Size(min=4, max=30, message = "Độ dài password nằm trong khoảng 4-20 kí tự")
    private String password;

    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message = "Số điện thoại không hợp lệ")
    private String phone;

    /*@JsonFormat(pattern = "yyyy/MM/dd", shape = JsonFormat.Shape.STRING)
    private Date birthday;*/
}
