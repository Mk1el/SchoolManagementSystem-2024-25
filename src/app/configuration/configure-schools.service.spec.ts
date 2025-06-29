import { TestBed } from '@angular/core/testing';

import { ConfigureSchoolsService } from './configure-schools.service';

describe('ConfigureSchoolsService', () => {
  let service: ConfigureSchoolsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConfigureSchoolsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
