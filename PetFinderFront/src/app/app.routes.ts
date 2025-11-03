import { Routes } from '@angular/router';
import { Login } from './login/login';
import { Home } from './home/home';
import { Register } from './register/register';
import { Store } from './store/store';
import { Checkout } from './checkout/checkout';
import { Map } from './map/map';

export const routes: Routes = [
    { path: '', component:Home},
    { path: 'login', component:Login },
    { path: 'register', component: Register},
    { path: 'store', component: Store},
    { path: 'checkout', component: Checkout},
    { path: 'map', component: Map}
];
