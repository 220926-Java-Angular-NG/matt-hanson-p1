package com.revature;

import java.util.Date;

class Ticket{
    public enum Status {PENDING, APPROVED, DENIED}

    Status status = Status.PENDING;
    long createdByFK = -1;
    long changedByFK = -1;
    long id = 0;
    double amount = 0;
    Date date;
    Date changedDate;

    Ticket(){

    }

    Ticket(long id, int createdByFK, double amount){
        this.id = id;
        this.createdByFK = createdByFK;
        this.status = Status.PENDING;
        this.amount = amount;
        this.date = new Date();
    }

}

class TicketReq{
    long id;
    boolean toApprove;

    public void decision(TicketReq ticketReq, Session session){
        Ticket ticket = new Ticket(); // FIND Ticket by ID in ticketReq

        if (ticketReq.toApprove)
            ticket.status = Ticket.Status.APPROVED;
        else
            ticket.status = Ticket.Status.DENIED;
        ticket.changedByFK = session.curUserFK;
        ticket.changedDate = new Date();
    }
}
