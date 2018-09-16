import { NgBootstrapModule } from './ng-bootstrap.module';

describe('NgBootstrapModule', () => {
  let ngBootstrapModule: NgBootstrapModule;

  beforeEach(() => {
    ngBootstrapModule = new NgBootstrapModule();
  });

  it('should create an instance', () => {
    expect(ngBootstrapModule).toBeTruthy();
  });
});
