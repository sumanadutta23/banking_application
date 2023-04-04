import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/user/dashboard/dashboard.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { AuthGuard } from './services/auth.guard';
import { ProfileComponent } from './components/profile/profile.component';
import { WelcomeComponent } from './components/user/welcome/welcome.component';
import { AccountComponent } from './components/user/account/account.component';
import { TransactionComponent } from './components/user/transaction/transaction.component';
import { FundComponent } from './components/user/fund/fund.component';

const routes: Routes = [
  
   { path: '', component: HomeComponent, pathMatch:'full'},
   { path: 'home', component: HomeComponent, pathMatch:'full'},
   { path: 'login', component: LoginComponent, pathMatch:'full'},
   { path: 'signup', component: SignupComponent, pathMatch:'full'},
   { path: 'dashboard', component: DashboardComponent, canActivate:[AuthGuard], children:[
    {
      path: '',
      component: WelcomeComponent,
    },
    {
      path: 'profile',
      component: ProfileComponent,
    },
    {
      path: 'account',
      component: AccountComponent,
    },
    {
      path: 'transaction',
      component: TransactionComponent,
    },
    {
      path: 'fund',
      component: FundComponent,
    }
   ] } 

   
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
