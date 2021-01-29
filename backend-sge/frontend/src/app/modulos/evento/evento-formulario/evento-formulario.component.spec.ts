import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventoFormularioComponent } from './evento-formulario.component';

describe('EventoFormularioComponent', () => {
  let component: EventoFormularioComponent;
  let fixture: ComponentFixture<EventoFormularioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventoFormularioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventoFormularioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
