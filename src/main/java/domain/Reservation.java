/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.validate.DomainError;
import domain.validate.ErrorList;
import domain.validate.Validate;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nigel
 */
@Entity
public class Reservation implements Validate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Person thePerson;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar arrivalDate, pickupDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateCreated;
    
    public Reservation() {
        dateCreated = Calendar.getInstance();
    }
    
    public Reservation(Person thePerson, Calendar arrivalDate, Calendar pickupDate) {
        this.thePerson = thePerson;
        this.arrivalDate = arrivalDate;
        this.pickupDate = pickupDate;
        dateCreated = Calendar.getInstance();
    }

    public Person getThePerson() {
        return thePerson;
    }

    public Calendar getArrivalDate() {
        return arrivalDate;
    }

    public Calendar getPickupDate() {
        return pickupDate;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setThePerson(Person thePerson) {
        this.thePerson = thePerson;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArrivalDate(Calendar arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setPickupDate(Calendar pickupDate) {
        this.pickupDate = pickupDate;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    public boolean isActive() {
        Calendar today = Calendar.getInstance();
        return this.getPickupDate().after(today);     
    }

    @Override
    public ErrorList validate() {
        ErrorList list = new ErrorList();
        if(arrivalDate == null) {
            list.setError(new DomainError("arrivalError", "Geef een geldige datum op"));
        }
        if(pickupDate == null ) {
            list.setError(new DomainError("pickupError", "Geef een geldige datum op"));
        }
        if(pickupDate.before(arrivalDate)) {
            list.setError(new DomainError("pickupError", "De vertrek datum moet na de aankomst datum zijn."));
        }
        // TODO: werkt niet helemaal lekker.
        if(arrivalDate.before(new Date())) {
            list.setError(new DomainError("pickupError", "De aankomst datum kan niet in het verleden zijn."));
        }
        return list;
    }
    
}
