export interface Dependent {
  id: number;
  name: string;
  birth: string;
  idEmployee: number;
}

export interface Employee {
  id: number;
  name: string;
  date: string;
  idSector: number;
  idImage: string | null;
  idUnit: number;
}

export interface Sector {
  id: number;
  name: string;
  launchDate: string;
}

export interface Unit {
  id: number;
  name: string;
  launchDate: string;
}
