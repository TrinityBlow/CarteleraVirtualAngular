import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterialTableGitComponent } from './material-table-git.component';

describe('MaterialTableGitComponent', () => {
  let component: MaterialTableGitComponent;
  let fixture: ComponentFixture<MaterialTableGitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MaterialTableGitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MaterialTableGitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
