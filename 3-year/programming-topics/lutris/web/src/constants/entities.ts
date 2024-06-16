import {
  Baby,
  User,
  Building,
  PanelsRightBottom,
  type LucideIcon,
} from "lucide-react";
import type { Dependent, Employee, Sector, Unit } from "~/@types/entities";
import { formatBRLDateTime } from "~/utils/date";

export type TranslatedEntities =
  (typeof ENTITIES_TYPES)[keyof typeof ENTITIES_TYPES];

export type Entities = keyof typeof ENTITIES_TYPES;

export type EntityMeta<T> = {
  columns: T[];
  translatedColumns: string[];
  icon: LucideIcon;
  key: TranslatedEntities;
  domain: Entities;
  title: string;
};

export const ENTITIES_TYPES = {
  dependents: "dependentes",
  employees: "colaboradores",
  sectors: "setores",
  units: "unidades",
} as const;

export const FORMATTERS = {
  dependents: (data: object): Dependent => {
    const d = data as Dependent;

    return {
      ...d,
      birth: formatBRLDateTime(d.birth),
    };
  },
  employees: (data: object): Employee => {
    const d = data as Employee;

    return {
      ...d,
      date: formatBRLDateTime(d.date),
    };
  },
  sectors: (data: object): Sector => {
    const d = data as Sector;

    return {
      ...d,
      launchDate: formatBRLDateTime(d.launchDate),
    };
  },
  units: (data: object): Unit => {
    const d = data as Unit;

    return {
      ...d,
      launchDate: formatBRLDateTime(d.launchDate),
    };
  },
};

const DEPENDENT_META: EntityMeta<keyof Dependent> = {
  columns: ["id", "name", "birth"],
  domain: "dependents",
  translatedColumns: ["ID", "Nome", "Aniversário"],
  icon: Baby,
  key: "dependentes",
  title: "Dependentes",
};

const EMPLOYEE_META: EntityMeta<keyof Employee> = {
  columns: ["id", "name", "date"],
  domain: "employees",
  translatedColumns: ["ID", "Nome", "Desde"],
  icon: User,
  key: "colaboradores",
  title: "Colaboradores",
};

const SECTOR_META: EntityMeta<keyof Sector> = {
  columns: ["id", "name", "launchDate"],
  domain: "sectors",
  translatedColumns: ["ID", "Nome", "Criado em"],
  icon: PanelsRightBottom,
  key: "setores",
  title: "Setor",
};

const UNIT_META: EntityMeta<keyof Unit> = {
  columns: ["id", "name", "launchDate"],
  domain: "units",
  translatedColumns: ["ID", "Nome", "Criado em"],
  icon: Building,
  key: "unidades",
  title: "Unidades",
};

export const ENTITIES_META: EntityMeta<string>[] = [
  DEPENDENT_META,
  EMPLOYEE_META,
  SECTOR_META,
  UNIT_META,
];
