import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePublicacionComponent } from './create-publicacion.component';

describe('CreatePublicacionComponent', () => {
  let component: CreatePublicacionComponent;
  let fixture: ComponentFixture<CreatePublicacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatePublicacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatePublicacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
