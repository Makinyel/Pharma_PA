import styled from "styled-components";
import { Link } from "react-router-dom";

export const TabsContainer = styled.div`
  ${({ isNotAPhone }) =>
    isNotAPhone && `margin-top: 2.5rem; margin-bottom: 1.25;`};
`;

export const TabsTitle = styled.div`
  ${({ isNotAPhone }) =>
    isNotAPhone &&
    `
    padding: 0 1.25rem;
  `}
`;

export const TabsLinkContainer = styled.div`
  border-bottom: 1px solid #1f2937;
  ${({ isNotAPhone }) => isNotAPhone && `padding-top: 3px;`}
`;

export const TabsLink = styled(Link)`
  display: inline-block;
  font-size: 0.875rem;
  font-weight: 500;
  padding: 1.25rem;
  text-decoration: none;
  color: #6b7280;
  transition: all 0.3s ease;

  &:hover {
    background-color: #f6f6f6;
    border-bottom: 2px solid #1f2937;
  }

  &.active-tabs {
    color: #1f2937;
    border-bottom: 2px solid #1f2937;
  }
`;
