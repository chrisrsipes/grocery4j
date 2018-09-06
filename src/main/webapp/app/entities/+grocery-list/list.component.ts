import { Component, OnInit } from '@angular/core';
import {GroceryListService} from '../../shared/auth/grocery-list.service';

@Component({
    selector: 'jhi-grocery-list-list',
    templateUrl: './list.component.html'
})
export class GroceryListListComponent implements OnInit {

    public groceryLists: any[];

    constructor(private groceryListService: GroceryListService) {
    }

    ngOnInit() {
        this.groceryListService
            .list()
            .subscribe((res) => {
                this.groceryLists = res.data;
            });
    }

}
