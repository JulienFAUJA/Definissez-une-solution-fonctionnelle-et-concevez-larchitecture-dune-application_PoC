import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  screen_orientation!: string;
  menuOpen: boolean = false;

  constructor() {}

  ngOnInit(): void {
    this.screen_orientation = window.screen.orientation.type;
    window.addEventListener('orientationchange', this.updateOrientation);
    this.updateOrientation(); // Appel initial pour définir l'orientation
  }

  ngOnDestroy(): void {
    window.removeEventListener('orientationchange', this.updateOrientation);
  }

  updateOrientation = (): void => {
    this.screen_orientation = window.screen.orientation.type;
  };


  
  toggleMenu(): void {
    this.menuOpen = !this.menuOpen;
    document.getElementById('sidebar')!.classList.toggle('open');
    document.getElementById('overlay')!.classList.toggle('show');
    document.body.classList.toggle('menu-open');
    if (this.menuOpen) {
      window.document.body.style.overflow = 'hidden'; // Désactive le défilement quand le menu est ouvert
    } else {
      window.document.body.style.overflow = 'auto'; // Réactive le défilement quand le menu est fermé
    }
  }
  
}
