import {Directive, OnInit, ElementRef} from "@angular/core";

declare let jQuery: any;

@Directive({
  selector: '[request-detail]'
})
export class RequestDetailDirective implements OnInit {
  private el: HTMLElement;

  constructor(ref: ElementRef) {
    this.el = ref.nativeElement;
  }

  ngOnInit(): void {
    this.el.addEventListener('click', e => {
      e.preventDefault();
      jQuery('#request-detail').modal({});
    });
  }

}