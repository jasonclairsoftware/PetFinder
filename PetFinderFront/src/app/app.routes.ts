import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { Home } from './pages/home/home';
import { Register } from './pages/register/register';
import { Store } from './pages/store/store';
import { Checkout } from './pages/checkout/checkout';
import { Map } from './pages/map/map';
import { Cart } from './pages/cart/cart';
import { Registerpet } from './pages/registerpet/registerpet';

export const routes: Routes = [
    { path: '', component:Home},
    { path: 'login', component:Login },
    { path: 'register', component: Register},
    { path: 'store', component: Store},
    { path: 'checkout', component: Checkout},
    { path: 'map', component: Map},
    { path: 'cart', component: Cart },
    { path: 'registerpet', component: Registerpet}
];
