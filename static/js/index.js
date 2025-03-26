/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

// create the Vue controller
const app = Vue.createApp();

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

import { sessionStore } from './session-store.js';
app.use(sessionStore);

// import authentication module
import { BasicAccessAuthentication } from './authentication.js';

// attach the controller to the <main> tag
app.mount("main");
