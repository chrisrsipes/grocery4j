import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import {GroceryListListComponent} from './+grocery-list/list.component';
import {RouterModule} from '@angular/router';
import {ENTITY_ROUTE} from './entity.route';
import {CommonModule} from '@angular/common';

/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        RouterModule.forRoot([ ENTITY_ROUTE ], { useHash: true }),
        CommonModule
    ],
    declarations: [ GroceryListListComponent ],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Grocery4JEntityModule {}
