import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgregarOwnersComponent } from './agregar-owners.component';

describe('AgregarOwnersComponent', () => {
  let component: AgregarOwnersComponent;
  let fixture: ComponentFixture<AgregarOwnersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgregarOwnersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgregarOwnersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
