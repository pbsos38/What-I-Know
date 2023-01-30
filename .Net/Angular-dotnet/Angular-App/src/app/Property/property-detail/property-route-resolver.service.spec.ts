/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { PropertyRouteResolverService } from './property-route-resolver.service';

describe('Service: PropertyRouteResolver', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PropertyRouteResolverService]
    });
  });

  it('should ...', inject([PropertyRouteResolverService], (service: PropertyRouteResolverService) => {
    expect(service).toBeTruthy();
  }));
});
