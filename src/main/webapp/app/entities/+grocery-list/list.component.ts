import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import {Principal} from "../../shared/auth/principal.service";
import {LoginModalService} from "../../shared/login/login-modal.service";


@Component({
    selector: 'grocery-list-list',
    templateUrl: './list.component.html'
})
export class GroceryListListComponent implements OnInit {

    constructor() {
    }

    ngOnInit() {
        console.log('list component initialized');
    }

}
