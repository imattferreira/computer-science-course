import dynamic from "next/dynamic";
import { notFound } from "next/navigation";
import { type ComponentType } from "react";
import { PageParams } from "~/@types/router";
import type { Entities } from "~/constants/entities";
import { getMetadata, isValidEntity } from "~/utils/entity";
import type { FormProps } from "../_components/Forms/shared/types";
import Title from "~/app/_components/Title";

interface Params {
  entityId: string;
  entity: string;
}

const FORMS: Record<Entities, ComponentType<FormProps<any>>> = {
  employees: dynamic(() => import("../_components/Forms/Employee")),
  dependents: dynamic(() => import("../_components/Forms/Dependent")),
  sectors: dynamic(() => import("../_components/Forms/Sector")),
  units: dynamic(() => import("../_components/Forms/Unit")),
};

async function Page({ params }: PageParams<Params>) {
  if (!isValidEntity(params.entity)) {
    return notFound();
  }

  const { icon, title, domain } = getMetadata(params.entity);
  const Form = FORMS[domain];

  return (
    <>
      <Title icon={icon}>{title}</Title>
      <Form />
    </>
  );
}

export default Page;
