import { NgPrimeModule } from './ng-prime.module';

describe('NgPrimeModule', () => {
  let ngPrimeModule: NgPrimeModule;

  beforeEach(() => {
    ngPrimeModule = new NgPrimeModule();
  });

  it('should create an instance', () => {
    expect(ngPrimeModule).toBeTruthy();
  });
});
