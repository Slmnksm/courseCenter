/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.cc.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SelmanKasim
 */
@Entity
@Table(name = "instructor_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InstructorInfo.findAll", query = "SELECT \u0131 FROM InstructorInfo \u0131"),
    @NamedQuery(name = "InstructorInfo.findById", query = "SELECT \u0131 FROM InstructorInfo \u0131 WHERE \u0131.id = :id"),
    @NamedQuery(name = "InstructorInfo.findByTitle", query = "SELECT \u0131 FROM InstructorInfo \u0131 WHERE \u0131.title = :title"),
    @NamedQuery(name = "InstructorInfo.findByExperience", query = "SELECT \u0131 FROM InstructorInfo \u0131 WHERE \u0131.experience = :experience"),
    @NamedQuery(name = "InstructorInfo.findByInfo", query = "SELECT \u0131 FROM InstructorInfo \u0131 WHERE \u0131.info = :info"),
    @NamedQuery(name = "InstructorInfo.findByReference", query = "SELECT \u0131 FROM InstructorInfo \u0131 WHERE \u0131.reference = :reference")})
public class InstructorInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "experience")
    private String experience;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "info")
    private String info;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "reference")
    private String reference;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userId;

    public InstructorInfo() {
    }

    public InstructorInfo(Integer id) {
        this.id = id;
    }

    public InstructorInfo(Integer id, String title, String experience, String info, String reference) {
        this.id = id;
        this.title = title;
        this.experience = experience;
        this.info = info;
        this.reference = reference;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InstructorInfo)) {
            return false;
        }
        InstructorInfo other = (InstructorInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tr.kasim.cc.model.InstructorInfo[ id=" + id + " ]";
    }
    
}
