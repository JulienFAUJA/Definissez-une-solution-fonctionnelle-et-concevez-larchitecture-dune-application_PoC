import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header-anonymous',
  templateUrl: './header-anonymous.component.html',
  styleUrls: ['./header-anonymous.component.scss']
})
export class HeaderAnonymousComponent implements OnInit {
  screen_orientation!:string;
  menuOpen:boolean = false;

  constructor() {

  }

  ngOnInit(): void {
    this.screen_orientation=window.screen.orientation.type;
    console.log(this.screen_orientation);window.addEventListener('orientationchange', this.updateOrientation);
    this.updateOrientation();  // Appel initial pour définir l'orientation
  }

  ngOnDestroy(): void {
    window.removeEventListener('orientationchange', this.updateOrientation);
  }

  updateOrientation = (): void => {
    this.screen_orientation = window.screen.orientation.type;
    console.log('Orientation changed to:', this.screen_orientation);
  }

  toggleMenu(): void {
    this.menuOpen = !this.menuOpen;
  }

  back():void{
    alert("retour...");
    
  }
}
