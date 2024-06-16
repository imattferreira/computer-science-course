"use client";
import { useParams } from "next/navigation";
import Item from "./Item";
import type { Optional } from "~/@types/utils";
import { ENTITIES_META, type TranslatedEntities } from "~/constants/entities";

function List() {
  const params = useParams<{ entity?: string }>();

  const entity = params.entity as Optional<TranslatedEntities>;

  return (
    <>
      {ENTITIES_META.map(({ icon, key, title }) => (
        <Item
          key={key}
          icon={icon}
          link={`/${key}`}
          title={title}
          active={entity === key}
        />
      ))}
    </>
  );
}

export default List;
