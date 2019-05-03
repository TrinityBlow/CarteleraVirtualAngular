import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VerCarteleraComponent } from './ver-cartelera.component';

describe('VerCarteleraComponent', () => {
  let component: VerCarteleraComponent;
  let fixture: ComponentFixture<VerCarteleraComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VerCarteleraComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VerCarteleraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
