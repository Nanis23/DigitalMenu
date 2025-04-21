import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    BrowserModule,

    RouterModule,
    // Other global imports
  ],
  exports: [
    BrowserModule,

    RouterModule,
    // Any modules you want to share globally
  ],
})
export class AppImportsModule {}
