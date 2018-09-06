import { Route } from '@angular/router';
import {GroceryListListComponent} from "./+grocery-list/list.component";

export const ENTITY_ROUTE: Route = {
    path: 'lists',
    component: GroceryListListComponent,
    data: {
        authorities: [],
        pageTitle: 'Grocery Lists'
    }
};
