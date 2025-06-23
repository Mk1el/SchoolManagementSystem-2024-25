import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfigSchoolsComponent } from './config-schools.component';

describe('ConfigSchoolsComponent', () => {
  let component: ConfigSchoolsComponent;
  let fixture: ComponentFixture<ConfigSchoolsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConfigSchoolsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfigSchoolsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
