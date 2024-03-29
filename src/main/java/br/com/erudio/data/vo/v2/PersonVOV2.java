package br.com.erudio.data.vo.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PersonVOV2 extends RepresentationModel<PersonVOV2> implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    @JsonProperty("id")
    private long key;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    private Date birthDay;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        PersonVOV2 that = (PersonVOV2) o;
        return this.getKey() == that.getKey() && Objects.equals(this.getFirstName(), that.getFirstName())
                && Objects.equals(this.getLastName(), that.getLastName())
                && Objects.equals(this.getAddress(), that.getAddress())
                && Objects.equals(this.getGender(), that.getGender())
                && Objects.equals(this.getBirthDay(), that.getBirthDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getKey(), this.getFirstName(), this.getLastName(), this.getAddress(), this.getGender(),
                this.getBirthDay());
    }

    public long getKey() {
        return this.key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDay() {
        return this.birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
