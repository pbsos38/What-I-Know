import { Component, OnInit } from '@angular/core';
import {ContactService} from '../contact.service';
import {Contact} from '../contact';
@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css'],
  providers: [ContactService]
})
export class ContactsComponent implements OnInit {

  contacts: Contact[] | undefined;
  contact: Contact  | undefined;
  first_name:String  | undefined;
  last_name:string  | undefined;
  phone:string  | undefined;

  constructor( private contactService:  ContactService) { }

  addContact(){
    const newContact = {
      first_name:this.first_name,
      last_name: this.last_name,
      phone: this.phone,
      this.contactService.getContacts()
          .subscibe( contacts =>
        this.contacts = contacts);
    }

    this.contactService.addContact(newContact)
    .subscibe(contact => {
      this.contacts.push(contact);
    });
  }

  deleteContact(id:any){
    var contacts = this.contact;
    this.contactService.deleteContact(id)
    .subscibe(data =>{
      if(data.n==1){
        for(var i=0; i<this.contacts.length;i++){
          if(this.contacts[i]._id == id){
            this.contacts.splice(i,1);
          }
        }
      }
    })
  }

  ngOnInit(): void {
    this.contactService.getContacts()
    .subscibe( contacts =>
      this.contacts = contacts);
  }

}
