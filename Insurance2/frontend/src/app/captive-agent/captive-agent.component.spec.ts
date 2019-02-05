import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CaptiveAgentComponent } from './captive-agent.component';

describe('CaptiveAgentComponent', () => {
  let component: CaptiveAgentComponent;
  let fixture: ComponentFixture<CaptiveAgentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CaptiveAgentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CaptiveAgentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
