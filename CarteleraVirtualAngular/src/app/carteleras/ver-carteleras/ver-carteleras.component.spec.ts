import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VerCartelerasComponent } from './ver-carteleras.component';

describe('VerCartelerasComponent', () => {
  let component: VerCartelerasComponent;
  let fixture: ComponentFixture<VerCartelerasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VerCartelerasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VerCartelerasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
