package ge.mimino.travel.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_types", schema = "university", catalog = "")
public class UserTypes {
    private int userTypeId;
    private String userTypeName;

    @Basic
    @Column(name = "user_type_id")
    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Basic
    @Column(name = "user_type_name")
    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTypes userTypes = (UserTypes) o;

        if (userTypeId != userTypes.userTypeId) return false;
        if (userTypeName != null ? !userTypeName.equals(userTypes.userTypeName) : userTypes.userTypeName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userTypeId;
        result = 31 * result + (userTypeName != null ? userTypeName.hashCode() : 0);
        return result;
    }
}
