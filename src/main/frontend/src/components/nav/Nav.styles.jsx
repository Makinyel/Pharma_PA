import styled from "styled-components";
import { NavLink } from "react-router-dom";

export const NavContainer = styled.div`
  background-color: #dbe2ef; //lightblue;
  padding: ${(props) => (props.isNotAPhone ? "2.5rem 1.25rem" : "1.25rem")};
  display: ${(props) => !props.isOpen && "flex"};
  flex-direction: ${(props) => !props.isOpen && "column"};
  align-items: ${(props) => props.isNotAPhone && !props.isOpen && "center"};
  @media (min-width: 1024px) {
    width: ${(props) => (props.isOpen ? "20%" : "6%")};
  }
`;

export const TitleContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

export const NavLinkContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: ${(props) => !props.isNotAPhone && "center"};
  gap: ${(props) => (props.isNotAPhone ? "1.25rem" : "0.5rem")};
  padding-top: ${(props) => (props.isNotAPhone ? "2.5rem" : "1.25rem")};
  height: ${(props) => props.isNotAPhone && "100%"};
`;

export const MyNavLink = styled(NavLink)`
  display: flex;
  gap: 0.5rem;
  padding: 0rem 1.25rem;
  border-radius: 0.375rem;
  transition: 0.5s ease-in-out;
  color: #112d4e;
  &:hover {
    background-color: #112d4e;
    color: #f9f7f7;
  }
  @media (min-width: 1024px) {
    width: 100%;
  }
`;

export const NavLinkActive = styled(MyNavLink)`
  background-color: #112d4e;
  color: #f9f7f7;
`;
