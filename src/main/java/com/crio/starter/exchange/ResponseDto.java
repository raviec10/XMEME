package com.crio.starter.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Objects;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto {

    private String id;

    @NotEmpty(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    private String name;

    @NotEmpty(message = "URL cannot be empty")
    @NotNull(message = "URL cannot be null")
    private String url;

    @NotEmpty(message = "Caption cannot be empty")
    @NotNull(message = "Caption cannot be null")
    private String caption;

  // private String message;

  // @Override
  // public boolean equals(Object o) {
  //   if (this == o) return true;
  //   if (o == null || getClass() != o.getClass()) return false;
  //   ResponseDto that = (ResponseDto) o;
  //   return Objects.equals(message, that.message);
  // }

  // @Override
  // public int hashCode() {
  //   return Objects.hash(message);
  // }
}
