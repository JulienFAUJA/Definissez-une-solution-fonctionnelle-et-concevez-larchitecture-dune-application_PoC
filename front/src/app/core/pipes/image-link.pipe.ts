import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';

@Pipe({
  name: 'imageLink'
})
export class ImageLinkPipe implements PipeTransform {

  constructor(private sanitizer: DomSanitizer) {}

  transform(value: string): SafeHtml {
    const urlPattern = /(https?:\/\/.*\.(?:png|jpg|jpeg|gif|svg))/i;
    const transformedValue = value.replace(urlPattern, '<img src="$1" alt="Image" style="max-width: 100%; height: auto;"/>');
    return this.sanitizer.bypassSecurityTrustHtml(transformedValue);
  }

}
