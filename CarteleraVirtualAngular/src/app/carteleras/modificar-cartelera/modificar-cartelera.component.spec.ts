import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificarCarteleraComponent } from './modificar-cartelera.component';

describe('ModificarCarteleraComponent', () => {
  let component: ModificarCarteleraComponent;
  let fixture: ComponentFixture<ModificarCarteleraComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModificarCarteleraComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModificarCarteleraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
